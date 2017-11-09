# python3
import random
from sys import stdin
# Splay tree implementation

# Vertex of a splay tree
class Vertex:
  def __init__(self, key, sum, left, right, parent):
    (self.key, self.sum, self.left, self.right, self.parent) = (key, sum, left, right, parent)

def update(v):
  if v == None:
    return
  v.sum = v.key + (v.left.sum if v.left != None else 0) + (v.right.sum if v.right != None else 0)
  if v.left != None:
    v.left.parent = v
  if v.right != None:
    v.right.parent = v

def smallRotation(v):
  parent = v.parent
  if parent == None:
    return
  grandparent = v.parent.parent
  if parent.left == v:
    m = v.right
    v.right = parent
    parent.left = m
  else:
    m = v.left
    v.left = parent
    parent.right = m
  update(parent)
  update(v)
  v.parent = grandparent
  if grandparent != None:
    if grandparent.left == parent:
      grandparent.left = v
    else: 
      grandparent.right = v

def bigRotation(v):
  if v.parent.left == v and v.parent.parent.left == v.parent:
    # Zig-zig
    smallRotation(v.parent)
    smallRotation(v)
  elif v.parent.right == v and v.parent.parent.right == v.parent:
    # Zig-zig
    smallRotation(v.parent)
    smallRotation(v)    
  else: 
    # Zig-zag
    smallRotation(v);
    smallRotation(v);

# Makes splay of the given vertex and makes
# it the new root.
def splay(v):
  if v == None:
    return None
  while v.parent != None:
    if v.parent.parent == None:
      smallRotation(v)
      break
    bigRotation(v)
  return v

# Searches for the given key in the tree with the given root
# and calls splay for the deepest visited node after that.
# Returns pair of the result and the new root.
# If found, result is a pointer to the node with the given key.
# Otherwise, result is a pointer to the node with the smallest
# bigger key (next value in the order).
# If the key is bigger than all keys in the tree,
# then result is None.
def find(root, key): 
  v = root
  last = root # I am not sure why sample code types last = root here, only if root is none at first
  next = None # only when the key is bigger than all keys
  while v != None:
    if v.key >= key and (next == None or v.key < next.key): #possible cause item leak 
      next = v    # next = the found key 
    last = v
    if v.key == key:
      break    
    if v.key < key:
      v = v.right
    else: 
      v = v.left      
  root = splay(last)  # I need to update global root, it does not update automatically 
  return (next, root) # next is the content of node (whether find out or not), root is the latest root

def split(root, key):  
  (result, root) = find(root, key)  #bring key to the top result = value, root = found key
  if result == None:    
    return (root, None)  
  right = splay(result) # right = the found key (root)
  left = right.left     # left = the left child of the found key
  right.left = None
  if left != None:
    left.parent = None
  update(left)
  update(right)
  return (left, right)
  
def merge(left, right):
  if left == None:
    return right
  if right == None:
    return left
  while right.left != None:
    right = right.left # find the smallest number
  right = splay(right) # put it to the root
  right.left = left # connect to the left 
  update(right)
  return right

  
# Code that uses splay tree to solve the problem
                                    
root = None # rebuild a tree

def printTree(root):
  def Trasverse(Node):
    if Node == None:
      return
    if Node.left == None and Node.right == None:
      print (Node.key,end = '')
      return
    print(Node.key,end = '')
    Trasverse(Node.left)
    Trasverse(Node.right)
  Trasverse(root)
  print()

def insert(x):
  global root
  (left, right) = split(root, x)
  new_vertex = None
  if right == None or right.key != x: # root of the right tree != insert or insert is the biggest
    new_vertex = Vertex(x, x, None, None, None)  
  root = merge(merge(left, new_vertex), right)
#  printTree(root)  

def erase(x): 
  if x == None:
    return
  global root
  # Implement erase yourself
  (findResult,root) = find(root,x)
  if findResult == None or findResult.key != x :
    return
  else:
    (leftLeft,mid) = split(root,x)
    (midLeft,midRight) = split(mid,x+1)
    mid == None 
    if leftLeft != None:
      leftLeft.parent == None
    if midRight != None:
      midRight.parent == None
    root = merge(leftLeft,midRight)
    update(root)
    return

def search(x): 
  global root
  # Implement find yourself
  (findResult,root) = find(root,x)
  if findResult == None:
    return False
  elif findResult.key != x:
    root = splay(findResult)
    return False
  return True
  
def sum(fr, to): 
  global root
  ans = 0
  # Complete the implementation of sum
  (left, middle) = split(root, fr)
  (middle, right) = split(middle, to + 1)
  if middle == None :
    temp = merge(left,middle)
    root = merge(temp,right) 
    return ans
  else:
    ans = middle.sum
  if left != None:
    left.parent == None
  if middle != None:
    middle.parent == None
  if right != None:
    right.parent == None
  temp = merge(left,middle)
  root = merge(temp,right) 
  update(root) 
  return ans

def cal(left,right,list):
  total = 0
  for i in list:
    if left <= i and i <= right:
      total += i 
  return total
def randomTest():
  opNumber = random.randint(0,3)
  operater = []
  if opNumber == 0:
    operater.append('+')
    operater.append(random.randint(0,100))
  elif opNumber == 1:
    operater.append('-')
    operater.append(random.randint(0,100))
  elif opNumber == 2:
    operater.append('?')
    operater.append(random.randint(0,100))
  elif opNumber == 3:
    operater.append('s')
    operater.append(random.randint(0,100))
    operater.append(random.randint(0,100))
  return operater

MODULO = 1000000001
testN = 10000
last_sum_result = 0
testList = []
last_sum_result = 0
for i in range(testN):
  line = randomTest()
  string = ' '.join(str(e) for e in line)
  print (string)
  if line[0] == '+':
    x = int(line[1])
    insert((x + last_sum_result) % MODULO)
    if not ((x + last_sum_result) % MODULO) in testList:
      testList.append(((x + last_sum_result) % MODULO))
  elif line[0] == '-':
    x = int(line[1])
    erase((x + last_sum_result) % MODULO)
    if ((x + last_sum_result) % MODULO) in testList:
      testList.remove(((x + last_sum_result) % MODULO))
  elif line[0] == '?':
    x = int(line[1])
#    print('Found' if search((x + last_sum_result) % MODULO) else 'Not found')
#    print('Found' if ((x + last_sum_result) % MODULO) in testList else 'Not found')
    if search((x + last_sum_result) % MODULO) != ((x + last_sum_result) % MODULO) in testList :
      print ('DS case: ', end = '')
      print (search((x + last_sum_result) % MODULO))
      print ('Test case: ', end = '')
      print (((x + last_sum_result) % MODULO) in testList)
      print ("Search!!")
      break
  elif line[0] == 's':
    l = int(line[1])
    r = int(line[2])
    res = sum((l + last_sum_result) % MODULO, (r + last_sum_result) % MODULO)
    testRes = cal((l + last_sum_result) % MODULO,(r + last_sum_result) % MODULO,testList) 
    if res != testRes:
      print ('DS case: ', end = '')
      print (res)
      print ('Test case: ', end = '')
      print (testRes)
      print ("Res!!")
      break
    last_sum_result = res % MODULO
