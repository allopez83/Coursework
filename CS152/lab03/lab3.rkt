#lang racket

; Part 1
; Using the map function, implement a strings-to-nums function.This function takes a list of string representations of numbersand converts them to the numbers themselves.

(define (strings-to-nums lst)
    (map string->number lst)
)

(strings-to-nums '("1" "2"))

; Part 2
; Using the foldl function along with cons, implement the reverse-list function.

(define (reverse-list lst)
    (foldl cons '() lst)
)

(reverse-list '(1 2 3 4))

; Part 3
; Note that the map function can also be applied to multiple items. Use map to append a list of first names and a list of last names to create a list of full names.

(define (name-concat first last)
    (doStuff)
)
(define firstN '("Great" "John" "Billy"))
(define lastN '("Houdini" "Doe" "Bob"))