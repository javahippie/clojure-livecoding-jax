-- :name create-rating! :! :n
-- :doc creates a new rating record
INSERT INTO rating
(id, name, rating, comment)
VALUES (:id, :name, :rating, :comment)

-- :name get-ratings :? :*
-- :doc retrieves all ratings
SELECT * FROM rating

-- :name get-rating :? :1
-- :doc retrieves a rating record given the id
SELECT * FROM rating
WHERE id = :id
