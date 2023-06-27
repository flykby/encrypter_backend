FROM golang:1.20


COPY . .
RUN go mod download
RUN go build -ldflags="-s -w" -o golang_server ./main/main.go 

EXPOSE 44555
CMD ["./golang_server"]
