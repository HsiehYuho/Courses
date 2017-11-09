# python3

class Request:
    def __init__(self, arrival_time, process_time):
        self.arrival_time = arrival_time
        self.process_time = process_time

class Response:
    def __init__(self, dropped, start_time):
        self.dropped = dropped
        self.start_time = start_time

class Buffer:
    def __init__(self, size):
        self.size = size
        self.finish_time_ = []

    def Process(self, request): # return drop or not and started time
        # write your code here
        '''
        if len(self.finish_time_) == 0:
        	self.finish_time_.append(request.arrival_time + request.process_time)
        	#print ("finish time = " + repr (self.finish_time_))
        	return Response(False,request.arrival_time)
        elif len(self.finish_time_) >= self.size and self.finish_time_[0] > request.arrival_time:
        	#print ("finish time = " + repr (self.finish_time_))
        	return Response(True,-1)
        else:
        '''	
       	while len(self.finish_time_)!= 0 and self.finish_time_[0] <= request.arrival_time: # I forget the "="" situation
       		self.finish_time_.pop(0)
       	if len(self.finish_time_) >= self.size and self.finish_time_[0] > request.arrival_time:
       		return Response(True,-1)
       	elif len(self.finish_time_) == 0:
       		self.finish_time_.append(request.arrival_time+request.process_time)
	        #	print ("finish time = " + repr (self.finish_time_))
       		return Response(False,request.arrival_time)
       	else:
       		self.finish_time_.append(self.finish_time_[-1]+request.process_time)
	        #	print ("finish time = " + repr (self.finish_time_))
       		return Response(False,self.finish_time_[-2])
        	
def ReadRequests(count): # put request objects into a list 
    requests = []
    for i in range(count):
        arrival_time, process_time = map(int, input().strip().split())
        requests.append(Request(arrival_time, process_time))
    return requests

def ProcessRequests(requests, buffer): # change request to dropped or not and assigned started time 
    responses = []
    for request in requests:
        responses.append(buffer.Process(request))
    return responses    # output responses

def PrintResponses(responses):
    for response in responses:
        print(response.start_time if not response.dropped else -1)

if __name__ == "__main__":
    size, count = map(int, input().strip().split())
    requests = ReadRequests(count) # requests is an list of object, which contains arrival time and process time 
#    print ("requests: " + repr(requests[0].process_time))
    buffer = Buffer(size)
    responses = ProcessRequests(requests, buffer) # put requests list into a buffer and output the dropped and started time attribute

    PrintResponses(responses)
