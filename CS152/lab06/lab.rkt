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
(define-syntax swap
    (syntax-rules ()
        [(swap x y)
            (let ([tmp x])
            (set! x y)
            (set! y tmp))]))
; (define a 1)
; (define b 2)
; (swap a b)
; (displayln a)
; (displayln b)

(define ns (make-base-namespace))

(define-syntax switch
    (syntax-rules ()
        
        [(switch val) (begin
            (displayln "problem")
            (error "no match!")
        )]
        [(switch val compA compB ...)
            (begin
                (displayln "loop")
                ; Going through switches
                (if (number? (car compA))
                    (if (= val (car compA))
                        (eval (cadr compA) ns)
                        (switch val compB ...)
                    )
                    ; Not a number, must be default. This is a horrible hackjob :(
                    (eval (cadr compA) ns)
                    ; (cadr compA)
                )
            )
        ]
    )
)

(define x 99)
(switch x
    '[3 (displayln "x is 3")]
    '[4 (displayln "x is 4")]
    '[5 (displayln "x is 5")]
    '[99 (displayln "x is 99")]
    '[default (displayln "none of the above")])

; (define a (displayln "action"))
; (eval a)
(define a '(displayln "run this"))
; (car a)
; (eval (car a))
; (eval (car a) (cadr a))
; (eval a ns)
; (eval a)