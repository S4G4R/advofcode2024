(ns advofcode2024.1.core
  "https://adventofcode.com/2024/day/1")

;; Part 1
(defn- parse
  [input]
  (->> (re-seq #"\d+" input)
       (map parse-long)
       (partition 2)
       (apply map vector)))

(defn- distance
  "Returns the absolute distance between `x` and `y`."
  [x y]
  (abs (- x y)))

(defn- lists-distances
  "Returns a seq of the distance between the first element of `xs` and
  the first element of `ys`, the second element of `xs` and `ys`, and so
  on until one of the colls is exhausted."
  [xs ys]
  (map distance xs ys))

(defn total-distance
  "Returns the total distance between two lists, defined as the sum of
  the distance between corresponding elements of the lists, after
  sorting them in ascending order."
  [input]
  (->> (parse input)
       (map sort)
       (apply lists-distances)
       (reduce +)))

;; Part 2
(defn- similarity-score
  "Returns a seq of each element in `xs` multiplied by its occurence
  frequency in `ys`."
  [xs ys]
  (->> (map (frequencies ys) xs)
       (map (fnil * 0 0) xs)))

(defn total-similarity-score
  "Returns the sum of the similarity scores of each row in the given
  lists."
  [input]
  (->> (parse input)
       (apply similarity-score)
       (reduce +)))

(defn solve
  [input]
  (println "Total distance:" (total-distance input))
  (println "Similarity score:" (total-similarity-score input)))
