# perceptron.py
# -------------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


# Perceptron implementation
import util
PRINT = True

class PerceptronClassifier:
    """
    Perceptron classifier.

    An example is represented by a feature vector which is a util.Counter.
    """

    def __init__(self, legalLabels, max_iterations):
        self.legalLabels = legalLabels
        self.type = "perceptron"
        self.max_iterations = max_iterations
        self.weights = {}
        for label in legalLabels:
            self.weights[label] = util.Counter()  # this is the data-structure you should use

    def setWeights(self, weights):
        assert len(weights) == len(self.legalLabels)
        self.weights = weights

    def classify(self, data):
        """
        Classifies each example in data as the label that most closely
        matches the weight vector for that label.
        Return a list of classes for the data
        """
        guesses = []
        for f in data:
            scores = util.Counter()
            for each_label in self.legalLabels:
                scores[each_label] = self.weights[each_label] * f
            guess = scores.argMax()  # this is y
            guesses.append(guess)
        return guesses

    def train(self, trainingData, trainingLabels, validationData, validationLabels):
        """
        The training loop for the perceptron passes through the training data
        several times and updates the weight vector for each label
        based on classification errors.

        Use the provided self.weights[label] data structure.

        Use self.legalLabels to iterate over all the labels.

        A training example is represented by a feature vector (f)
        which is a util.Counter.
        You can print a feature vector (f) to see what it looks like.
        Here's an example:
        {(7, 3): 0, (20, 25): 0, (16, 9): 1, (19, 4): 0, (17, 20): 1, ...}

        """
        # DO NOT ZERO OUT YOUR WEIGHTS BEFORE STARTING TRAINING, OR
        # THE AUTOGRADER WILL LIKELY DEDUCT POINTS.

        # Don't mind the validation data, it's to test accuracy, not actually train with

        for iteration in range(self.max_iterations):
            print "Starting iteration ", iteration, "..."

            # Go through the different sets of training data
            index = 0
            for f in trainingData:
                trainingLabel = trainingLabels[index]  # this is y*
                index += 1

                "*** YOUR CODE HERE.  Do NOT change anything above ***"

                # Keep testing until f is correctly guessed
                correct = False
                while not correct:
                    # Make a guess
                    scores = util.Counter()
                    for labels in self.legalLabels:
                        scores[labels] = self.weights[labels] * f
                    guess = scores.argMax()
                    # Adjust if incorrect
                    if guess != trainingLabel:
                        print "Incorrect guess: ", guess, "vs", trainingLabel
                        # Aggressive corrections initially to provide numbers
                        if iteration == 0:
                            for l in self.legalLabels:
                                if l != trainingLabel:
                                    self.weights[l] -= f
                        # Decrease incorrect guess
                        else:
                            self.weights[guess] -= f
                        # Increase correct response
                        self.weights[trainingLabel] += f
                    # Otherwise the weights are correct
                    else:
                        correct = True;
            
            # print self.weights


# For reference, this will print 3
# testCounter = util.Counter()
# testCounter['a'] = 1
# testCounter['b'] = 2
# print testCounter.totalCount()

# Counter
# print type(self.weights[trainingLabel])
