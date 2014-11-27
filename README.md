# bidi-wrapper-matcher

This is a clojure implementation of [defrecord-wrapper.aop.Matcher protocol](https://github.com/tangrammer/defrecord-wrapper/blob/master/src/defrecord_wrapper/aop.clj#L4-L5)

Using this bidi based wrapper matcher you can apply middleware to  your clojure.core/defrecord functions implementations using [juxt/bidi](https://github.com/juxt/bidi) way

## Usage

```clojure
(ns your-ns
  (:require [bidi-wrapper-matcher.bidi-matcher :as bm]))

(defn logging-access-protocol
  [*fn* this & args]
  (println ">>>>>>>>>> LOGGING-ACCESS" [this args] (meta *fn*))
  (apply *fn* (conj args this))
  )

(defn  bye
  [*fn* this & args]
  (println ">>>>>>>>>> BYE FUNCTION" (meta *fn*))
  (apply *fn* (conj args this))
  )

(def routes ["" {"defrecord_wrapper.model.Welcome/say_bye/e/a/b" bye
                         "defrecord_wrapper.with_slash.prot.With_This" logging-access-protocol
                         "defrecord_wrapper.model"
                         {"" logging-access-protocol
                          ".Welcome"
                          {"" logging-access-protocol
                           "/greetings/_" logging-access-protocol}

                          }}])


(def bidi-matcher (bm/new-bidi-matcher :routes routes))

```

## Releases and Dependencies Information
```clojure
[tangrammer/bidi-wrapper-matcher "0.1.0-SNAPSHOT"]
```

```clojure
:dependencies [[prismatic/schema "0.3.2"]
               [bidi "1.10.4"]]
```
## License

Copyright © 2014 Juan Antonio Ruz (juxt.pro)

Distributed under the [MIT License](http://opensource.org/licenses/MIT). This means that pieces of this library may be copied into other libraries if they don't wish to have this as an explicit dependency, as long as it is credited within the code.
