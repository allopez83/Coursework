#lang racket

; Using define-syntax, create a switch statement.
; Sample usage:
; (define x 99)
; (switch x
;     [3 (displayln "x is 3")]
;     [4 (displayln "x is 4")]
;     [5 (displayln "x is 5")]
;     ['default (displayln "none of the above")])
; There is no starter code for this assignment.

; Example of define-syntax
; (define-syntax swap
;     (syntax-rules ()
;         [(swap x y)
;             (let ([tmp x])
;             (set! x y)
;             (set! y tmp))]))
; (define a 1)
; (define b 2)
; (swap a b)
; (displayln a)
; (displayln b)

(define ns (make-base-namespace))

(define-syntax switch
    (syntax-rules ()
        [(_) (error "no params!")]
        [(switch val) (error "no cases!")]
        ; [(switch val compA compB ...)
        [(switch val (case body) compB ...)
            (begin
                ; (displayln "pass")
                ; Going through switch cases
                (cond
                    [(number? case)
                        (if (equal? val case)
                            (eval body ns)
                            (switch val compB ...))]
                    [(equal? 'default case) (eval body ns)]
                    [else (error "no match!")]))]))

(define x 99)
(switch x
    [3 (displayln "x is 3")]
    [4 (displayln "x is 4")]
    [5 (displayln "x is 5")]
    [99 (displayln "x is 99")]
    ['default (displayln "none of the above")])
