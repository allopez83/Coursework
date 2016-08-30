#!/bin/sh
racket test.rkt > output
racket bc.rkt < input >> output
diff output output_EXPECTED

