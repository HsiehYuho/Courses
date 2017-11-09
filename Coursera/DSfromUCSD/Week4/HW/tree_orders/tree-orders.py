# python3

import sys, threading
sys.setrecursionlimit(10**6) # max depth of recursion
threading.stack_size(2**27)  # new thread will get stack of such size

class TreeOrders:
  def read(self):
    self.n = int(sys.stdin.readline())
    self.key = [0 for i in range(self.n)]
    self.left = [0 for i in range(self.n)]
    self.right = [0 for i in range(self.n)]
    for i in range(self.n):
      [a, b, c] = map(int, sys.stdin.readline().split())
      self.key[i] = a
      self.left[i] = b
      self.right[i] = c

  def inOrder(self):
    self.result = []
    # Finish the implementation
    # You may need to add a new recursive method to do that
    def InOrderTraversal(i):
      if i == -1 :
        return
      if self.left[i] == -1 and self.right[i] == -1:
        self.result.append(self.key[i])
        return
      InOrderTraversal(self.left[i])
      self.result.append(self.key[i])
      InOrderTraversal(self.right[i])
    InOrderTraversal(0)
    return self.result

  def preOrder(self):
    self.result = []
    # Finish the implementation
    # You may need to add a new recursive method to do that
    def PreOrderTraversal(i):
      if i == -1 :
        return
      if self.left[i] == -1 and self.right[i] == -1:
        self.result.append(self.key[i])
        return
      self.result.append(self.key[i])
      PreOrderTraversal(self.left[i])
      PreOrderTraversal(self.right[i])
    PreOrderTraversal(0)
    return self.result

  def postOrder(self):
    self.result = []
    # Finish the implementation
    # You may need to add a new recursive method to do that
    def PostOrderTraversal(i):
      if i == -1 :
        return
      if self.left[i] == -1 and self.right[i] == -1:
        self.result.append(self.key[i])
        return
      PostOrderTraversal(self.left[i])
      PostOrderTraversal(self.right[i])
      self.result.append(self.key[i])
    PostOrderTraversal(0)
    return self.result

def main():
	tree = TreeOrders()
	tree.read()
	print(" ".join(str(x) for x in tree.inOrder()))
	print(" ".join(str(x) for x in tree.preOrder()))
	print(" ".join(str(x) for x in tree.postOrder()))

threading.Thread(target=main).start()
