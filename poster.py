#!/usr/local/bin/python
import requests

i=9
while i < 11:
	API_ENDPOINT = "http://localhost:9000/fake/publish"
	data = {'id':i, 'text':'jouda89'}
	r = requests.post(url = API_ENDPOINT, json = data)
	pastebin_url = r.text 
	print("The pastebin URL is:%s"%pastebin_url) 
	i+=1