# bidi-wrapper-matcher

This is a clojure implementation of [defrecord-wrapper.aop.Matcher protocol](https://github.com/tangrammer/defrecord-wrapper/blob/master/src/defrecord_wrapper/aop.clj#L4-L5)

Using this bidi based wrapper matcher you can apply middleware to  your clojure.core/defrecord functions implementations using [juxt/bidi](https://github.com/juxt/bidi) way

[tangrammer/defrecord-wrapper](https://github.com/tangrammer/defrecord-wrapper)

## Usage

# Release and Dependencies Information
```clojure
[tangrammer/bidi-wrapper-matcher "0.1.0-SNAPSHOT"]
```

```clojure
:dependencies [[prismatic/schema "0.3.2"]
               [bidi "1.10.4"]]
```
## License

Copyright © 2014 Juan Antonio Ruz

Copyright © 2014 Juan Antonio Ruz (juxt.pro)

Distributed under the [MIT License](http://opensource.org/licenses/MIT). This means that pieces of this library may be copied into other libraries if they don't wish to have this as an explicit dependency, as long as it is credited within the code.
