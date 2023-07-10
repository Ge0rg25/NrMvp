FROM java:17
LABEL authors="beaver"

ENTRYPOINT ["top", "-b"]