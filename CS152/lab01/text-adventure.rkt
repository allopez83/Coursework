#lang racket

;; A very minimal text adventure game.

(define kitchen '("kitchen" "knife" "pot" "plate"))
(define small-room '("small room" "table" "chair"))
(define cavern '("cavern" "monster!"))

(define (list-items items)
  (cond [(= 0 (length items)) "nothing"]
        [(= 1 (length items)) (string-append "a " (car items))]
        [else (string-append "a " (car items) ", " (list-items (cdr items)))]))

(define (describe-room room)
  (let ([name (car room)]
        [items (cdr room)])
    (displayln (string-append "You are in a " name))
    (displayln (string-append "In the room, there is " (list-items items)))))

(define (show-menu room-name)
  (displayln room-name)
  (displayln "What action do you take?")
  (displayln "  1: Go to kitchen")
  (displayln "  2: Go to small room")
  (displayln "  3: Go to cavern")
  (if (eqv? room-name "cavern")
      (displayln "  4: Attack monster")
      '())
  (display "Your choice? "))

(define (attack)
  (displayln "You attack the monster with your sword and strike a mortal blow.")
  (displayln "You are hailed as a hero by all the land!")
  (displayln "Thanks for playing"))

(define (get-answer)
  (letrec ([input (read-line)])
    (displayln input)
    (string->number input)))

(define (one-turn room)
  (let ([room-name (car room)])
    (describe-room room)
    (show-menu room-name)
    (let ([ans (get-answer)])
      (cond
        [(= ans 1) (one-turn kitchen)]
        [(= ans 2) (one-turn small-room)]
        [(= ans 3) (one-turn cavern)]
        [(and (= ans 4) (eqv? room-name "cavern")) (attack)]
        [else (displayln "Sorry, I did not understand your choice") (one-turn room)]))))

(displayln "Welcome to Scheme-Dungeon")
(one-turn small-room)
