(ns advofcode2024.3.core
  "https://adventofcode.com/2024/day/3")

;; Part 1
(defn- parse
  [input]
  (->> (re-seq #"mul\((\d+),(\d+)\)" input)
       (map rest)
       (map #(map parse-long %))))

(defn- multiply
  "Returns the result of multiplying all the given operands."
  [operands]
  (reduce * operands))

(defn dot-product
  "Given a list of instructions of the form `mul(x,y)` embedded in a
  string, parses them and returns their dot product."
  [input]
  (->> (parse input)
       (map multiply)
       (reduce +)))

;; Part 2
(defn- parse-with-conditions
  [input]
  (->> (re-seq #"(mul\(\d+,\d+\))|(don't\(\))|(do\(\))" input)
       (mapcat rest)
       (remove nil?)))

(defn- enabled-commands
  "Given a seq of commands including `do()`, `don't()` and `mul(x,y)`,
  returns only the enabled `mul(x,y)` commands."
  ([commands enabled?]
   (lazy-seq
    (when-let [[command & rest] commands]
      (cond
        (= command "don't()")
        (enabled-commands rest false)

        (= command "do()")
        (enabled-commands rest true)

        enabled?
        (lazy-seq (cons command (enabled-commands rest true)))

        (not enabled?)
        (enabled-commands rest false)))))
  ([commands]
   (enabled-commands commands true)))

(defn conditional-dot-product
  "Like `dot-product`, but handles intermediate `don't()` and `do()`
  commands, which toggle off and on the `mul(x,y)` commands,
  respectively."
  [input]
  (->> (parse-with-conditions input)
       enabled-commands
       (mapcat parse)
       (map multiply)
       (reduce +)))

(defn solve
  [input]
  (println "Dot product:" (dot-product input))
  (println "Conditional dot product:" (conditional-dot-product input)))
