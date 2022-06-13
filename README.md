# lease-miles-calculator

## Getting Started

### Local Development

```bash
# Start Server
$ sbt server/run
# ...
# --- (Running the application, auto-reloading is enabled) ---
# [info] p.c.s.AkkaHttpServer - Listening for HTTP on /0:0:0:0:0:0:0:0:9000
# (Server started, use Enter to stop and go back to the console...)

```

### Example Request

```bash
$ curl "http:localhost:9000/lease/term-balance?currentMiles=3001"
# {"balance":{"monthly":5337,"total":27014},"lease":{"month":10,"today":{"date":"6/12/2022","time":"8:39:42 PM"}}}
```
