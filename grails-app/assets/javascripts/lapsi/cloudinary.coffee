
#Provide cloudinary config
cloudinaryConfig =
  cloudName: "dl9tlckg9"
  presentName: "contenttools"
  uploadUrl: 'https://api.cloudinary.com/v1_1/dl9tlckg9/image/upload'

lapsi.declare "cloudinaryConf", cloudinaryConfig


#Provide cloudinary service
lapsi.define "cloudinary", ["jquery", "cloudinaryConf"], ($, conf) ->
  cl = cloudinary.CloudinaryJQuery.new();
  cl.config( "cloud_name", conf.cloudName);

  cl.upload = (file, progressListener) ->
    formData = new FormData
    formData.append 'file', file
    formData.append 'upload_preset', conf.presentName

    promise = $.ajax(
        xhr: ->
          xhr = new XMLHttpRequest
          xhr.upload.addEventListener 'progress', progressListener ?= _.noop
          return xhr

        url: conf.uploadUrl
        data: formData
        cache: false
        contentType: false
        processData: false
        dataType: 'json'
        type: 'POST')

    return promise

  return cl
