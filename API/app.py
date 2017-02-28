#!flask/bin/python
from flask import Flask, jsonify, abort, make_response, request

app = Flask(__name__)

speakers = [
	{
		'id': 1,
		'name': u'Wojciech Czerpak'
	},{
		'id': 2,
		'name': u'Michal Zajac'
	}
]

@app.errorhandler(404)
def not_found(error):
	return make_response(jsonify({'error': 'Not found'}), 404)

@app.route('/sesja/14/speakers', methods=['GET'])
def get_speakers():
	return jsonify({'speakers':speakers})

@app.route('/sesja/14/speakers/<int:speaker_id>')
def get_speaker(speaker_id):
	speaker = [s for s in speakers if s['id'] == speaker_id]
	if not speaker:
		abort(404)
	return jsonify({'speaker': speaker[0]})

@app.route('/sesja/14/speakers', methods=['POST'])
def create_speaker():
	if not request.json or not 'name' in request.json:
		abort(400)
	speaker = {
        'id': speakers[-1]['id'] + 1,
        'name': request.json['name']
	}
	speakers.append(speaker)
	return jsonify({'speaker': speaker}), 201

if __name__ == "__main__":
	app.run(debug=True)