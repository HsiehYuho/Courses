# Uses python3
import sys
from collections import namedtuple
from operator import itemgetter, attrgetter

Segment = namedtuple('Segment', 'start end')

def optimal_points(segments):
    points = []
    #write your code here
    sortSegments = sorted(segments,key = attrgetter('start'))
#    print (sortSegments)
#    print (sortSegments.pop(0).start)
#    print (sortSegments)
#    while len(sortSegments) > 0:
#        currentPoint = sortSegments[0].start
#        points.append(sortSegments[0].start)
#        reduceSet = []
#        for s in sortSegments:
#            if currentPoint >= s.start and currentPoint <= s.end:
#                reduceSet.append(s)
#            sortSegments = list(set(sortSegments) - set(reduceSet)) 
#        print (sortSegments)
#        sortSegments = sorted(sortSegments,key = attrgetter('start','end'))               
    i = len(sortSegments)-1
    while i > -1:
        currentPoint = sortSegments[i].start
        points.append(sortSegments[i].start)
        while currentPoint >= sortSegments[i-1].start and currentPoint <= sortSegments[i-1].end:
            if i > 1:
                i = i - 1
            else:
                break
        if i == 1:
            if currentPoint >= sortSegments[i-1].start and currentPoint <= sortSegments[i-1].end:
                break
        i = i - 1
    points = list(set(points))
    points.sort()
    return points


#        while (sortSegments[i][0] <= sortSegments[i+1][1]) and (sortSegments[i][0] >= sortSegments[i+1][0]):
#            if i == len(sortSegments)-2:
#                break
#            else:
#                i = i+1
#        i = i +1

    
    

#    for s in segments:
#       points.append(s.start)
#       points.append(s.end)
    
if __name__ == '__main__':
    input = sys.stdin.read()
    n, *data = map(int, input.split())
    segments = list(map(lambda x: Segment(x[0], x[1]), zip(data[::2], data[1::2])))
    points = optimal_points(segments)
    print(len(points))
    for p in points:
        print(p, end=' ')
