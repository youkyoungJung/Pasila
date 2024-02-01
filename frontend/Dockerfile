FROM node:20.10.0 as build-stage
#폴더 위치
RUN mkdir -p /app
WORKDIR /app
ADD . .

#yarn 설치
RUN yarn install
RUN yarn run build

# production stage
FROM nginx:stable-alpine as production-stage
COPY  ./nginx/nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 5173
CMD ["nginx", "-g", "daemon off;"]