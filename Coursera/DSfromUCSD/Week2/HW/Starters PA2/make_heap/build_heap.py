# python3

class HeapBuilder:
  def __init__(self):
    self._swaps = []
    self._data = []

  def ReadData(self):
    n = int(input())
    self._data = [int(s) for s in input().split()]
    assert n == len(self._data)

  def WriteResponse(self):
    print(len(self._swaps))
    for swap in self._swaps:
      print(swap[0], swap[1])

  def GenerateSwaps(self):
    # The following naive implementation just sorts 
    # the given sequence using selection sort algorithm
    # and saves the resulting sequence of swaps.
    # This turns the given array into a heap, 
    # but in the worst case gives a quadratic number of swaps.
    #
    # TODO: replace by a more efficient implementation
    def Parent(i):
      return (i-1)//2
    def LeftChild(i):
      return 2*i+1
    def RightChild(i):
      return 2*i+2
    def ShiftDown(i):
      minIndex = i
      l = LeftChild(i)
      r = RightChild(i)
      if l < len(self._data) and self._data[minIndex]>self._data[l]:
        minIndex = l
      if r < len(self._data) and self._data[minIndex]>self._data[r]:
        minIndex = r
      if i != minIndex:
        self._swaps.append((i,minIndex))
        self._data[minIndex] , self._data[i] = self._data[i],self._data[minIndex]
#        print(repr(self._data))
        ShiftDown(minIndex)
    for i in range(len(self._data)-1//2,-1,-1):
      ShiftDown(i)
  def Solve(self):
    self.ReadData()
    self.GenerateSwaps()
    self.WriteResponse()

if __name__ == '__main__':
    heap_builder = HeapBuilder()
    heap_builder.Solve()


'''
    def ShiftUp(i):
      while i > 0 and self._data[Parent(i)]>self._data[i]:
        print ("i = " + repr(i) + "Parent i = " + repr (Parent(i)))
        print ("D[i] = " + repr(self._data[i]) + "D [Parent i] = " + repr(self._data[Parent(i)]))
        self._swaps.append((Parent(i),i))
        self._data[Parent(i)] , self._data[i] = self._data[i],self._data[Parent(i)]
        i = Parent(i)
'''

    
#    for i in range(len(self._data)-1,0,-1):
#      ShiftUp(i)
#      print (repr(self._data))
'''
    for i in range(len(self._data)):
      for j in range(i + 1, len(self._data)):
        if self._data[i] > self._data[j]:
          self._swaps.append((i, j))
          self._data[i], self._data[j] = self._data[j], self._data[i]
'''
