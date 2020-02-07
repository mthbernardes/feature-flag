# feature-flag

A Clojure library which defines a `defn` with feature-flag. 

## Usage
Basically you can provide a boolean to determine if the function is enabled or not.

```clojure
(ns feature-flag.usage
  (:require [feature-flag.core :as c]))

(c/defn enabled true [x]
  (println (str "Hello " x)))

(c/defn disabled false [x]
  (println (str "Goodbye" x)))

(enabled "banana") => "Hello banana"

(disabled "banana") => nil
```

Another approach is use the `config.edn`.

```
{:functions
 {:feature-flag.usage/enabled false
  :feature-flag.usage/disabled true}}
```

This configuration file has the big priority, so if you define in the code that the function is enabled and on the file you specify it as disabled, the function will be disabled.

## Warning
Don't use this in production. This library is just an personal project without intentions to be something else.
