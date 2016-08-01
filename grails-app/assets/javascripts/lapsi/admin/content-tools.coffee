lapsi.define "cloudinaryImageUploader", ["jquery", "$log", "cloudinary"], ($, $log, cloudinary) ->

  image = {}

  (dialog) ->
    $log.debug "Content tools image uploader called"

    jqHxr = null

    dialog.addEventListener 'imageuploader.fileready', (ev) ->
      $log.debug "File ready Event called"

      file = ev.detail().file

      dialog.state 'uploading'

      dialog.progress 0

      xhrProgress = (ev) -> dialog.progress ev.loaded / ev.total * 100

      jqHxr = cloudinary.upload(file, xhrProgress)

      jqHxr.done (data) ->
        $log.debug "Cloudinary response received", data
        image = data
        image.url = cloudinary.url(data.public_id, {crop: 'fit', width: 600, height: 600})
        $log.debug "Cloudinary image object", image
        dialog.populate image.url, [image.width, image.height]

      return

    dialog.addEventListener 'imageuploader.save', ->
      $log.debug "Cloudinary", cloudinary
      transformation = cloudinary.transformation()

      if (image.width > 400 or image.height > 400)
        transformation.crop("fit").width(400).height(400)
        ratio = Math.min(400 / image.width, 400 / image.height)
        image.width *= ratio;
        image.height *= ratio;

        image.url = cloudinary.url(image.public_id, transformation)

      dialog.save image.url, [image.width, image.height]
      return

    dialog.addEventListener 'imageuploader.cancelupload', ->
      $log.debug 'Image upload cancelled'
      jqHxr?.abort()
      dialog.state 'empty'
      return

    dialog.addEventListener 'imageuploader.clear', () ->
      dialog.clear()
      image = null



lapsi.define "imageResizer", ["jquery", "cloudinary", "$log"], ($, cloudinary, $log) ->

  (element) ->
    #$log.debug element
    return if element.type() != 'Image'
    $log.debug element.size()
    #src = element.attrs("src")
    #$log.debug src


lapsi.define "PageCtrl", ["jquery", "$log", "cloudinaryImageUploader", "imageResizer"], ($, $log, imageUploader, imageResizer)->

  () ->
    $log.debug "PageCtrl initialized"

    ContentTools.StylePalette.add [new (ContentTools.Style)('author', 'author', ['p'])]

    editor = ContentTools.EditorApp.get()
    editor.init '*[data-editable]', 'data-name'

    editor.addEventListener 'saved', (ev) ->
      regions = ev.detail().regions
      if _.keys(regions).length == 0 then return

      editor.busy true

      promise = $.ajax
        type: 'POST'
        url: $(location).attr('href')
        data: JSON.stringify(regions: regions)
        contentType: 'application/json'
        dataType: 'json'

      promise.done (data, status, xhr)->
        $log.debug "Page saved", arguments
        editor.busy false

        $.publish("page:saved")

        new (ContentTools.FlashUI)('ok')


    ContentTools.IMAGE_UPLOADER = imageUploader
    #ContentEdit.Root.get().bind 'taint', imageResizer
