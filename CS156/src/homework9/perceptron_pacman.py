# perceptron_pacman.py
# --------------------
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


# Perceptron implementation for apprenticeship learning
import util
from perceptron import PerceptronClassifier
from pacman import GameState

PRINT = True


class PerceptronClassifierPacman(PerceptronClassifier):
    def __init__(self, legalLabels, maxIterations):
        PerceptronClassifier.__init__(self, legalLabels, maxIterations)
        self.weights = util.Counter()

    def classify(self, data ):
        """
        Data contains a list of (features, legal moves)
        
        features is a Counter representing the features of each GameState.
        legalMoves is a list of legal moves for that GameState.
        """
        guesses = []
        for features, legalMoves in data:
            scores = util.Counter()
            for each_action in features:
                scores[each_action] = self.weights * features[each_action]
            guesses.append(scores.argMax())
        return guesses


    def train( self, trainingData, trainingLabels, validationData, validationLabels ):
        """
        Data contains a list of (features, legal moves)

        legalMoves is a list of legal moves for that GameState.

        features is a Counter representing the features of each GameState.
        You can print a feature vector (f) to see what it looks like.
        Here's an example:
        {'West': {'foodCount': 96}, 'East': {'foodCount': 96}, 'Stop': {'foodCount': 97}
        To access the feature vector corresponding to a given action, we need:
        features[action]
        """

        # DO NOT ZERO OUT YOUR WEIGHTS BEFORE STARTING TRAINING, OR
        # THE AUTOGRADER WILL LIKELY DEDUCT POINTS.

        for iteration in range(self.max_iterations):
            print "Starting iteration ", iteration, "..."
            index = 0
            for features, legalMoves in trainingData:
                chosen_action =  trainingLabels[index] # corresponding expert action
                index += 1
                "*** YOUR CODE HERE - Do not modify anything above this line***"
