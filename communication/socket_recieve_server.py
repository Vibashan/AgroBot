import socket               

#host= "10.1.82.48"
host= "192.168.43.103"
#host= "10.1.76.196"

port=8006
data = None
conn = None

sock=socket.socket(socket.AF_INET, socket.SOCK_STREAM, 0)
sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)

sock.bind((host,port))
sock.listen(10)

print(host,port)

conn, addr = sock.accept()
print(conn, addr)
#conn.sendall('hello \n')
#conn.sendall('hello \n')

#conn.sendall(b'hello')
#sock.send(b'hello')

while True:
    #data = sock.recvfrom(1024)
    data = conn.recv(1024)
    #data = sock.recv(1024)
    #data = data.decode('utf-8')
    data = str(data)
    values = data.split(' ')
    #print(values[3], len(data))
    a = values[0]
    b = values[1]
    c = values[2]
    d = values[3]
    
    #if conn is not None:
    #    print("in")
    #    conn.send('hello')
    
    #if data is not None:
    #    conn.send("recieved")

sock.close()    
