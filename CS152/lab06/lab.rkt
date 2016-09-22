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
        [(switch val)
            (begin
                (displayln "problem")
                (error "no cases!"))]
        ; [(switch val compA compB ...)
        [(switch val (case body) compB ...)
            (begin
                (displayln "pass")
                ; Going through switches
                (cond
                    [(number? case)
                        (if (= val case)
                            (eval body ns)
                            (switch val compB ...))]
                    ; Not a number, must be default. This is horrible hackjob logic :(
                    [(equal? 'default case) (eval body ns)]
                    [else (error "no match!")]))]))

; Switch usage:
; Switch is given a value, and will try to match with subsequent lists
; Lists are in the format: '(MATCH, ACTION) where if match is the same as the initial value, the action will be taken. The last list should have default for the match, and this catches cases that fall through.

(define x 99)
(switch x
    [3 (displayln "x is 3")]
    [4 (displayln "x is 4")]
    [5 (displayln "x is 5")]
    [99 (displayln "x is 99")]
    ['default (displayln "none of the above")])

; (define a (displayln "action"))
; (eval a)
; (define a '(displayln "run this"))
; (car a)
; (eval (car a))
; (eval (car a) (cadr a))
; (eval a ns)
; (eval a)
