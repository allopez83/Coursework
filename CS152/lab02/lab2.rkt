#lang racket

;; Reverse the order of items in a list.
(define (reverse lst)
    ; (displayln (car lst))
    (cond
        [(< 1 (length lst)) (append (reverse (cdr lst)) (list (car lst)))]
        [else (list (car lst))]
    )
)


;; Join two lists together by adding their elements.
;; If one list is longer than the other, add the
;; additional elements to the end of the list.
(define (add-two-lists lst1 lst2)
    ; (displayln (car lst1))
    ; (displayln (car lst2))
    (cond
        [(and (< 1 (length lst1)) (< 1 (length lst2)))
            (append
                (list (+ (car lst1) (car lst2)))
                (add-two-lists (cdr lst1) (cdr lst2))
            )
        ]
        [else
            (append
                (list (+ (car lst1) (car lst2)))
                (cdr lst1)
                (cdr lst2)
            )
        ]
    )
)


;; Return the list with only positive number included.
(define (positive-nums-only lst)
    ; (displayln lst)
    (cond
        [(< 1 (length lst))
            (cond
                [(< 0 (car lst))
                    (append (list (car lst)) (positive-nums-only (cdr lst)))
                ]
                [else (positive-nums-only (cdr lst))]
            )
        ]
        [else
            (cond
                [(< 0 (car lst)) lst]
                [else '()]
            )
        ]
    )
)


;; Should return '(4 3 2 1)
(reverse '(1 2 3 4))

;; Should return '(10 2 5 5 5)
(add-two-lists '(1 2 3 4 5) '(9 0 2 1 0))

;; Should return '(8 6 1)
(add-two-lists '(5 4) '(3 2 1))

;; Should return '(15 17 18 16)
(add-two-lists '(13 14 15 16) '(2 3 3))

(positive-nums-only '(1 2 -4 90 -4))
