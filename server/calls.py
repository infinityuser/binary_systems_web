from aiohttp import web
import aiohttp
import asyncio as aio
import json   
import random 
import os

async def html_template(fileName):
	s = open('templates/'+fileName, 'r')
	return web.Response(text=s.read(), content_type='text/html')

async def index(request):
	return await html_template('index.html')

async def api_call(request):
	letters = [chr(i) for i in range(65, 90)]
	out = '../model/temp/' + ''.join([random.choice(letters) for i in range(30)])+'.out'
	args = '300000 ' + ' '.join(['%.3f' % float(i) for i in request.query.values()])
	process = await aio.create_subprocess_shell(f'../model/main.kexe {args} > {out}')
	await process.wait()

	ws = web.WebSocketResponse()
	await ws.prepare(request) 

	cnt = 0
	with open(out) as file:
		async for msg in ws:
			line = file.readline().strip()
			data = {}
			while line != '_' and line and cnt < 199:
				line = line.split()
				data[line[0]] = line[1]
				line = file.readline().strip()
			if msg.type == aiohttp.WSMsgType.TEXT:
				if msg.data == 'close' or len(data) == 0:
					await ws.close()
				else:
					await ws.send_json(data)
					cnt += 1
			elif mst.type == aiohttp.WSMsgType.ERROR:
				pass
	#os.remove(out)
	
	return ws
