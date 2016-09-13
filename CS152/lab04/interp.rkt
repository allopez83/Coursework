#lang racket

;; Expressions in the language
(struct b-val (val))
(struct b-if (c thn els))

;; Main evaluate method
(define (evaluate prog)
  (match prog
    [(struct b-val (v)) v]
    [(struct b-if (c thn els))
     (if (evaluate c)
         (evaluate thn)
         (evaluate els))]
    [_ (error "Unrecognized expression")]))

(evaluate (b-val #t))
(evaluate (b-val #f))
(evaluate (b-if (b-val #t)
                (b-if (b-val #f) (b-val #t) (b-val #f))
                (b-val #f)))


;; Consider the following sample programs for extending the interpreter
; succ 1  =>  returns 2
; succ (succ 7) => returns 9
; pred 5 => returns 6
; succ (if true then 42 else 0) => 43

; succ takes number and returns next highest
; pred takes number returns next lowest

(define (succ num)
    (+ num 1))

(define (pred num)
    (- num 1))

(succ 1)
(succ (succ 7))
(pred 5)
; (succ (if true then 42 else 0))
