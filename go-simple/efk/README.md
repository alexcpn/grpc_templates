

# How to generate the Configs

```
git clone https://github.com/fluent/fluentd-kubernetes-daemonset.git
```

You can find the Images referred below in the Make filter

```
fluentd-kubernetes-daemonset$

docker run --rm -i -v /home/alexpunnen/fluentd-kubernetes-daemonset/templates/conf/kubernetes.conf.erb:/kubernetes.conf.erb:ro ruby:alpine erb -U -T 1 dockerfile='v1.12/debian-elasticsearch6' version='v1.12.4-debian-elasticsearch6-amd64-1.1' /kubernetes.conf.erb


docker run --rm -i -v /home/alexpunnen/fluentd-kubernetes-daemonset/templates/conf/fluent.conf.erb:/fluent.conf.erb:ro ruby:alpine erb -U -T 1 dockerfile='v1.12/debian-elasticsearch6' version='v1.12.4-debian-elasticsearch6-amd64-1.1' /fluent.conf.erb
```
