(ns bidi-wrapper-matcher.bidi-matcher
  (:require [defrecord-wrapper.aop :refer (Matcher)]
            [defrecord-wrapper.reflect :as r]
            [clojure.string :as str ]
            [schema.core :as s]
            [bidi.bidi :refer (match-route)]
            [clojure.test :refer :all]))

;; TODO this can be simplified with matching tangrammer.MyInterface/my-function[arg0 arg1 arg3]
;; I mean using vector as arguments path... maybe to work in bidi only translate to bidi dialect before invoking bidi
(defn get-match-options
  "match-options tries to match as log4j-back does
  So for example this value: tangramer.MyInterface/my-function/arg0/arg1/arg2
  will find matchs for:
  1. tangramer.MyInterface/my-function/arg0/arg1/arg2
  2. tangramer.MyInterface/my-function
  3. tangramer.MyInterface
  4. tangramer
  Taking the high priority result "
  [protocol function-name function-args]
  (let [interface-name (r/java-interface-name protocol)
        base (str/split (r/java-interface-name protocol) #"\.")]
    (-> (reduce (fn [c i]
               (let [n (str/join "." [(last c) i] )]
                 (conj c n))) [(first base)] (next base))
        (conj (str interface-name "/" function-name "/"  (str/join "/"  function-args)))
        (conj (str/replace (str interface-name "/" function-name "/"  (str/join "/"  function-args)) #"_" "this"))
        sort
        reverse)))


(defrecord BidiMatcher [routes]
  Matcher
  (match [this protocol function-name function-args]
    (->> (get-match-options protocol function-name function-args)
         (some #(match-route routes %))
         :handler)))


(defn new-bidi-matcher [& {:as opts}]
  (->> opts
       (s/validate  {:routes [(s/one s/Str "") (s/one {s/Str s/Any} {})]})
       map->BidiMatcher))
