(ns menu 
  (:require [clojure.string :as str]))


(defn wait-for-key
  "Wait for a key to be pressed"
  []
  (println "\nPress enter to go back to the sales menu...")
  (read-line)
  )

(defn display-menu
  []
  (println 
"
*** Sales Menu *** 
------------------
1. Display Customer Table
2. Display Product Table
3. Display Sales Table
4. Total Sales for Customer
5. Total Count for Product
6. Exit
 
Enter an option?
"))

(defn display-customer
  "Display customer database"
  [customerData]
  (println "***Customers Table***")
  (println "-----------------------")
  (doseq [item customerData]
   (doseq [i (str/split item #"\|")]
    (print i " ")
     )
    (println)
  )
  (wait-for-key)
  )

(defn display-product
  "Display product table"
  [data] 
  (println "***Products Table***")
  (println "----------------------")
  (let [productData (get data :product)]
    (doseq [item productData]
      (doseq [item2 (str/split item #"\|")]
        (print item2 " "))
      (println))
  (wait-for-key)
  ))

(defn get-customer-name-by-id
  "returns the customer when given an id"
  [id customers]
  (let [customer (filter #(= id (first (str/split % #"\|"))) customers)]
    (if (empty? customer)
      "Unknown Customer"
      (second (str/split (first customer) #"\|"))))
  )

(defn get-product-name-by-id
  "returns the product when given an id"
  [id products]
  (let [product (filter #(= id (first (str/split % #"\|"))) products)]
    (if (empty? product)
      "Unknown Product"
      (second (str/split (first product) #"\|"))))
  )

(defn get-customer-id-given-name
  "returns the customer id when given a name"
  [name customers]
  (let [customer (filter #(= name (second (str/split % #"\|"))) customers)]
    (if (empty? customer)
      "Unknown Customer"
      (first (str/split (first customer) #"\|"))))
  )

(defn get-item-price-given-id
  "returns the item price when given an id"
  [id products] 
  (let [product (filter #(= id (first (str/split % #"\|"))) products)]
    (if (empty? product)
      "Unknown Product"
      (last (str/split (first product) #"\|"))))
  )

(defn calculate-customer-total
  "Calculate the total sales for a given customer id"
  [id data] 
  (reduce + (map #(let [saledetails (str/split % #"\|")]
                    (if (= id (second saledetails))
                      (* (parse-double 
                          (get-item-price-given-id 
                           (nth saledetails 2) 
                           (get data :product))) 
                         (Integer/parseInt (last saledetails)))
                      0))
                 (get data :sale))))

(defn display-customer-total-sales-amount-given-id
  "Display the total sales for a given customer id"
  [id data] 
  (print "\nTotal Sales for Customer: " (get-customer-name-by-id id (get data :customer)))
  (println "\n$" (calculate-customer-total id data))
  (wait-for-key)
  )

(defn display-sales
  "Display sales table"
  [data] 
  (println "***Products Table***")
  (println "----------------------")
  (let [salesData (get data :sale)]
    (doseq [sale salesData]
      (let [saledetails (str/split sale #"\|")]
        (print (nth saledetails 0) " ")
        (print (get-customer-name-by-id (nth saledetails 1) (get data :customer)) " ")
        (print (get-product-name-by-id (nth saledetails 2) (get data :product)) " ")
        (print (nth saledetails 3)  " ")
        (println)
        )))
  (wait-for-key)
  )