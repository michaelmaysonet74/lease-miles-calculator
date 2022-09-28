# lease-miles-calculator

## Getting Started

### Local Development

```bash
# Start Server
$ sbt server/run
# ...
# --- (Running the application, auto-reloading is enabled) ---
# [info] p.c.s.AkkaHttpServer - Listening for HTTP on /0:0:0:0:0:0:0:0:8090
# (Server started, use Enter to stop and go back to the console...)

```

### Example Request

```bash
$ curl "http://localhost:8090/lease/term-balance?currentMiles=5667"
# {
#   "balance": {
#     "monthly": 5172,
#     "total": 24348
#   },
#   "lease": {
#     "year": 1,
#     "month": 1,
#     "today": {
#       "date": "9/28/2022",
#       "time": "3:40:56 PM"
#     }
#   }
# }
```
