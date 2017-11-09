# python3
import numpy as np #diminesional array

class JobQueue:
    def read_data(self):
        self.num_workers, m = map(int, input().split())
        self.jobs = list(map(int, input().split()))
        assert m == len(self.jobs)

    def write_response(self):
        for i in range(len(self.jobs)):
          print(self.assigned_workers[i], self.start_times[i]) 

    def assign_jobs(self):
        self.assigned_workers = [None] * len(self.jobs)
        self.start_times = [None] * len(self.jobs)
        workerQueue = np.zeros((self.num_workers,2),int)
        for i in range (self.num_workers):
          workerQueue[i][1] = i    #(0,0), (0,1), (0,2), (0,3) ..... (starttime , worker index)
        try :
          import Queue as Q
        except ImportError:
          import queue as Q

        heapq = Q.PriorityQueue()
        for i in range (self.num_workers):
          heapq.put((0,i))
        for i in range (0,len(self.jobs),1):
          root = heapq.get()
          self.assigned_workers[i] = (root[1])
          self.start_times[i] = (root[0])
          temp = list(root)
          temp[0] += self.jobs[i]
          root = tuple(temp)
          heapq.put(root)
        
        def shiftDown(i):  # sort piority (sort root is okay, root is the next free worker with least ID)
          minIndex = i
          l = 2*i+1 # left child
          r = 2*i+2 # right child 
          if  l < len(workerQueue) and  workerQueue[minIndex][0] == workerQueue[l][0]:  #  for single left child and no right child  workerQueue[start time][worker ID]
            if workerQueue[minIndex][1] > workerQueue[l][1] and r >= len(workerQueue) :
              minIndex = l
          if l < len(workerQueue) and r < len (workerQueue) and workerQueue[r][0] == workerQueue[l][0]:#  for hiving left child and right child  when they are both free next time, pick the smaller ID one 
            if workerQueue[r][1] < workerQueue[l][1]:
              minIndex = r
            else:
              minIndex = l
          if l < len(workerQueue) and workerQueue[minIndex][0] > workerQueue[l][0]:
            minIndex = l
          if r < len(workerQueue) and workerQueue[minIndex][0] > workerQueue[r][0]:
            minIndex = r
          if i != minIndex:
            for j in range (0,2,1):
              workerQueue[minIndex][j],workerQueue[i][j] = workerQueue[i][j],workerQueue[minIndex][j]
            shiftDown(minIndex)        

#        for i in range (0,len(self.jobs),1):
#          self.assigned_workers[i] = workerQueue[0][1]
#          self.start_times[i] = workerQueue[0][0]
#          workerQueue[0][0] += self.jobs[i]   
#          shiftDown(0)
    def solve(self):
        self.read_data()
        self.assign_jobs()
        self.write_response()

if __name__ == '__main__':
    job_queue = JobQueue()
    job_queue.solve()

