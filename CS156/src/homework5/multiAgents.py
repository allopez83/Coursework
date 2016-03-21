# multiAgents.py
# --------------
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


from util import manhattanDistance
from game import Directions
import random, util, sys

from game import Agent


def scoreEvaluationFunction(currentGameState):
    """
      This default evaluation function just returns the score of the state.
      The score is the same one displayed in the Pacman GUI.

      This evaluation function is meant for use with adversarial search agents
      (not reflex agents).
    """
    return currentGameState.getScore()


class MultiAgentSearchAgent(Agent):
    """
      This class provides some common elements to all of your
      multi-agent searchers.  Any methods defined here will be available
      to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

      You *do not* need to make any changes here, but you can if you want to
      add functionality to all your adversarial search agents.  Please do not
      remove anything, however.

      Note: this is an abstract class: one that should not be instantiated.  It's
      only partially specified, and designed to be extended.  Agent (game.py)
      is another abstract class.
    """

    def __init__(self, evalFn = 'scoreEvaluationFunction', depth = '2'):
        self.index = 0 # Pacman is always agent index 0
        self.evaluationFunction = util.lookup(evalFn, globals())
        self.depth = int(depth)

class MinimaxAgent(MultiAgentSearchAgent):
    """
      Your minimax agent (question 1)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action from the current gameState using self.depth
          and self.evaluationFunction.

          Here are some method calls that might be useful when implementing minimax.

          gameState.getLegalActions(agentIndex):
            Returns a list of legal actions for an agent
            agentIndex=0 means Pacman, ghosts are >= 1

          gameState.generateSuccessor(agentIndex, action):
            Returns the successor game state after an agent takes an action

          gameState.getNumAgents():
            Returns the total number of agents in the game
        """
        result = self.value(gameState, 0, 0)
        # print "for getAction:%s"  # result
        return result[1]  # (value, action)

    def value(self, state, agent, descent):
        if agent >= state.getNumAgents():
            agent = 0
            descent += 1

        if descent == self.depth:
            return self.evaluationFunction(state)  # terminal state

        if agent == 0: # pacman
            return self.maxValue(state, agent, descent)
        else:
            return self.minValue(state, agent, descent)

    def maxValue(self, state, agent, descent):
        results = []
        value = -sys.maxsize

        if not state.getLegalActions(agent):
            return self.evaluationFunction(state)

        for action in state.getLegalActions(agent):
            successorState = state.generateSuccessor(agent, action)
            successorVal = self.value(successorState, agent + 1, descent)
            
            if type(successorVal) is tuple:
                successorVal = successorVal[0]
            
            value = max(value, successorVal)
            results.append((value, action))

        # Sort results by value desc, with highest at first position
        results.sort(key=lambda tup: tup[0], reverse=True)
        # print "Max %s for agent %s" % (str(results[0]), agent)
        return results[0]

    def minValue(self, state, agent, descent):
        results = []
        value = sys.maxsize

        if not state.getLegalActions(agent):
            return self.evaluationFunction(state)

        for action in state.getLegalActions(agent):
            successorState = state.generateSuccessor(agent, action)
            successorVal = self.value(successorState, agent + 1, descent)
            
            if type(successorVal) is tuple:
                successorVal = successorVal[0]
            
            value = min(value, successorVal)
            results.append((value, action))

        # Sort results by value asc, with lowest at first position
        results.sort(key=lambda tup: tup[0])
        # print "Min %s for agent %s" % (str(results[0]), agent)
        return results[0]


class AlphaBetaAgent(MultiAgentSearchAgent):
    """
      Your minimax agent with alpha-beta pruning (question 2)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action using self.depth and self.evaluationFunction
        """
        "*** YOUR CODE HERE ***"
        result = self.value(gameState, 0, 0, -sys.maxsize, sys.maxsize)
        # print "for getAction:%s"  # result
        return result[1]  # (value, action)

    def value(self, state, agent, descent, alpha, beta):
        if agent >= state.getNumAgents():
            agent = 0
            descent += 1

        if descent == self.depth:
            return self.evaluationFunction(state)  # terminal state

        if agent == 0: # pacman
            return self.maxValue(state, agent, descent, alpha, beta)
        else:
            return self.minValue(state, agent, descent, alpha, beta)

    def maxValue(self, state, agent, descent, alpha, beta):
        results = []
        value = -sys.maxsize

        if not state.getLegalActions(agent):
            return self.evaluationFunction(state)

        for action in state.getLegalActions(agent):
            successorState = state.generateSuccessor(agent, action)
            successorVal = self.value(successorState, agent + 1, descent, alpha, beta)
            
            if type(successorVal) is tuple:
                successorVal = successorVal[0]
            
            value = max(value, successorVal)

            if value > beta:
                return value

            alpha = max(alpha, value)

            results.append((value, action))

        # Sort results by value desc, with highest at first position
        results.sort(key=lambda tup: tup[0], reverse=True)
        # print "Max %s for agent %s" % (str(results[0]), agent)
        return results[0]

    def minValue(self, state, agent, descent, alpha, beta):
        results = []
        value = sys.maxsize

        if not state.getLegalActions(agent):
            return self.evaluationFunction(state)

        for action in state.getLegalActions(agent):
            successorState = state.generateSuccessor(agent, action)
            successorVal = self.value(successorState, agent + 1, descent, alpha, beta)
            
            if type(successorVal) is tuple:
                successorVal = successorVal[0]
            
            value = min(value, successorVal)

            if value < alpha:
                return value

            beta = min(beta, value)

            results.append((value, action))

        # Sort results by value asc, with lowest at first position
        results.sort(key=lambda tup: tup[0])
        # print "Min %s for agent %s" % (str(results[0]), agent)
        return results[0]
