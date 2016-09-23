## Program

Racket auto-generated functions

`account-balance`: function that gets the balance field from the account
`account?`: boolean that verifies that you have an account.

## Contracts

`balance`, `withdraw`, `deposit` only get accounts passed in
`withdraw` and `deposit` only get positive numbers
`withdraw` and `deposit` return an account
`balance` returns a number
`withdraw` doesn't receive number that exceeds available funds

### `balance`

- account passed in
- returns number

## `deposit`

- account passed in
- positive num passed in
- return account

## `withdraw`

- account passed in
- positive num passed in
- return account
- doesn't withdraw more than available funds