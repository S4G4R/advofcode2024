(ns advofcode2024.1.core
  "https://adventofcode.com/2024/day/1")

;; Part 1

(defn- lists
  [input]
  (->> (re-seq #"\d+" input)
       (map parse-long)
       (partition 2)
       (apply map vector)))

(defn- distance
  [x y]
  (abs (- x y)))

(defn- lists-distances
  [xs ys]
  (map distance (sort xs) (sort ys)))

(defn- total-distance*
  [xs]
  (apply + xs))

(defn total-distance
  [input]
  (->> (lists input)
       (apply lists-distances)
       total-distance*))

;; Part 2

(defn- appearance-count
  [xs]
  (-> (group-by identity xs)
      (update-vals count)))

(defn- similarity-score*
  [xs ys-appearance-count]
  (reduce (fn [acc x]
            (+ acc (* x (get ys-appearance-count x 0))))
          0
          xs))

(defn similarity-score
  [input]
  (->> (lists input)
       ((juxt first #(appearance-count (second %))))
       (apply similarity-score*)))

(defn solve
  [input]
  (println "Total distance:" (total-distance input))
  (println "Similarity score:" (similarity-score input)))
