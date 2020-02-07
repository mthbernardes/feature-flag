(ns feature-flag.core
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [clojure.edn :as edn]))

(def config (-> "config.edn" io/resource slurp edn/read-string :functions))

(defn- read-flag [function status]
  (let [function-name (-> function
                          .toString
                          (string/replace #"_" "-")
                          (string/replace #"\$" "/")
                          (string/split #"@")
                          first
                          keyword)]
    (if (contains? config function-name)
      (function-name config)
      status)))

(defmacro defn [name status params & body]
  `(defn ~name ~params
     (let [~'*fn-name* (str ~name)]
       (if (true? (and (read-flag ~name ~status) status))
         (do ~@body)
         ~params))))
