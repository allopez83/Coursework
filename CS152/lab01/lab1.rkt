#lang racket

(define (max-num lst)
    ; (displayln lst)
    (cond
        [(= 1 (length lst)) (car lst)]
        [(< (car lst) (cadr lst)) (max-num (cons (cadr lst) (cddr lst)))]
        [else (max-num (cons (car lst) (cddr lst)))]))

;; The function counts from 1 to the specified number, returning a string with the result.
;; The rules are:
;;    If a number is divisible by 3 and by 5, instead say "fizzbuzz"
;;    Else if a number is divisible by 3, instead say "fizz"
;;    Else if a number is divisible by 5, instead say "buzz"
;;    Otherwise say the number
(define (fizzbuzz n)
  (print (fizzbuzz1 1 n)))

;; Helper function for fizzbuzz
(define (fizzbuzz1 i n)
    ; (displayln i)
    ; (displayln n)    
    (cond
        [(= 1 n) "1"] ; only 1
        [(> i n) ""] ; end
        [(= 0 (+ (modulo i 3) (modulo i 5)) ) (string-append "fizzbuzz " (fizzbuzz1 (+ i 1) n))] ; horrendous mess
        [(= 0 (modulo i 3)) (string-append "fizz " (fizzbuzz1 (+ i 1) n))] ; mod 3
        [(= 0 (modulo i 5)) (string-append "buzz " (fizzbuzz1 (+ i 1) n))] ; mod 5
        [else (string-append (string-append (number->string i) " ") (fizzbuzz1 (+ i 1) n))]
    )
)

; Playground
; (fizzbuzz 15)

; Run
(max-num '(1 2 3 4 5))
(max-num '(-5 -3 -2 -13))
(fizzbuzz 21)
