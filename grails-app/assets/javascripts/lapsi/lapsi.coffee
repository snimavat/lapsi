window.lapsi = new Pod();
lapsi.declare("jquery", $);


#controllers
jQuery ($) ->
  div = $('div[controller]')
  $.each div, (index, elem) ->
    $elem = $(elem)

    controllerName = $elem.attr('controller')

    controllerConstructor = lapsi.require(controllerName)
    controller = new controllerConstructor($elem)


#Initialize page controller
jQuery ($) ->
  pageController = lapsi.require("PageCtrl")
  new pageController() #Execute main page controller

