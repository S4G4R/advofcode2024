(ns advofcode2024.3.core-test
  (:require
   [advofcode2024.3.core :as core]
   [clojure.java.io :as io]
   [clojure.test :refer :all]))

(def sample-input
  "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")
(def sample-input-2
  "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")

(deftest dot-product-test
  (testing "Sample input"
    (is (= 161       (core/dot-product sample-input)))
    (is (= 171183089 (core/dot-product (slurp (io/resource "3/input.txt")))))))

(deftest conditional-dot-product-test
  (testing "Actual input"
    (is (= 48        (core/conditional-dot-product sample-input-2)))
    (is (= 63866497  (core/conditional-dot-product (slurp (io/resource "3/input.txt")))))))
