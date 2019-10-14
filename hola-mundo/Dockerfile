FROM node:8.11-alpine
# RUN apk --no-cache add npm 
#	&& npm install express-generator -g \
#	&& express --view=ejs hola-mundo \
#	&& cd hola-mundo/ \
#	&& npm install \
#	&& npm start

WORKDIR /usr/hello-world/
COPY package.json ./

RUN npm install

COPY . .

CMD npm start

EXPOSE 3000
