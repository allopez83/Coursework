#lang racket
(provide pos-to-string)
;; You are given a list of numbers. Return a list of all POSITIVE
;; elements, converted to strings. (This is the same function as in
;; the preceding problem.)

;; Now do NOT use recursion. Use higher-order functions instead.

(define (pos-to-string nums)
    (flatten (map filter nums))
)

(define (filter num)
    (if (positive? num)
        (number->string num)
        empty
    )
)

; (pos-to-string '(11 -23 58 0 12))
