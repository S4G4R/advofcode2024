(ns advofcode2024.2.core-test
  (:require
   [advofcode2024.2.core :as core]
   [clojure.java.io :as io]
   [clojure.test :refer :all]))

(def sample-input
  "7 6 4 2 1
   1 2 7 8 9
   9 7 6 2 1
   1 3 2 4 5
   8 6 4 4 1
   1 3 6 7 9")

(deftest safe-count-test
  (testing "Sample input"
    (is (= 2   (core/safe-count sample-input))))
  (testing "Actual input"
    (is (= 269 (core/safe-count (slurp (io/resource "2/input.txt")))))))

(deftest dampened-safe-count-test
  (testing "Sample input"
    (is (= 4   (core/dampened-safe-count sample-input))))
  (testing "Actual input"
    (is (= 337 (core/dampened-safe-count (slurp (io/resource "2/input.txt")))))))
