from aiohttp import web
from config import configs

from aiohttp_jinja2 import setup
import jinja2

from calls import index, api_call

async def create_app(config_name):
	app = web.Application()
	app['config'] = configs[config_name]
	setup(app, loader=jinja2.FileSystemLoader('templates/'))

	app.router.add_get('/', index)
	app.router.add_view('/api', api_call)
	app.router.add_static('/static', './static', name='static')
	app['static_root_url'] = './static' 
	return app

if __name__ == '__main__':
	web.run_app(create_app('default'))
