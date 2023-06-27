FROM golang:1.20


COPY . .
RUN go mod download
RUN go build -ldflags="-s -w" -o golang_server ./main/main.go 

EXPOSE 3002
CMD ["./golang_server"]
