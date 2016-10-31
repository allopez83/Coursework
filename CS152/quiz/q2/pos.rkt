#lang racket
(provide pos-to-string)
;; You are given a list of numbers. Return a list of all POSITIVE
;; elements, converted to strings.
;; Hint: (number->string n)

;; For example, (pos-to-string '(1 -2 0 3)) returns the list
;; ("1" "3").

;; Use recursion! 

(define (pos-to-string nums)
    (if (< 0 (length nums))
        ; Append positive
        (append
            (if (< 0 (car nums))
                (list (number->string (car nums)))
                '()
            )
            (pos-to-string (cdr nums))
        )
        ; Ignore negative
        '()
    )
)

; (pos-to-string '(1 -2 0 3))
