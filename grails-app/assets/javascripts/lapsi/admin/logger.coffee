lapsi.define "$log", ->
  info:  -> console.info.apply(console, arguments)
  debug: -> console.debug.apply(console, arguments)
  error: -> console.error.apply(console, arguments)
