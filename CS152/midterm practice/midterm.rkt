#lang racket

; Problem 7

; (define (largest-elem lst)
;     (cond
;         [(equal? (length lst) 0) (error "empty list!")]
;         [(> (length lst) 1)
;             (largest-elem
;                 (append
;                     ; Find larger element
;                     (list (if (> (car lst) (cadr lst))
;                         (car lst)
;                         (cadr lst)))
;                     (cddr lst)))
;         ]
;         [else (car lst)]
;     )
; )
; (largest-elem '(1 2 3))
; (largest-elem '(1 3 2 4 1))
; (largest-elem '())

; Problem 8

(define (mycond cond thn els)
  (if cond thn els))

(define x 3)

(mycond
    (= x 3)
    (displayln "x is 3")
    (displayln "x is NOT 3")
)

; Problem 9
