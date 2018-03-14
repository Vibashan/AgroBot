import socket               

host="192.168.43.103"
port=2055

sock=socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)

sock.bind((host,port))

while True:
    data,addr = sock.recvfrom(1024)
    data = data.decode('utf-8')
    data = str(data)
    values = data.split(',')

    print(values[0])
