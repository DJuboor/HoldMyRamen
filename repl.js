var arDrone = require('ar-drone');
var client  = arDrone.createClient();
var fs = require('fs');
client.config('video:video_channel', 3);

client.takeoff(function() {

	client.front(0.25);
	client.after(1600, function() { client.stop();
		take_pic(client, 0,1);

		client.after(1600, function(){ client.front(0.2); });
		client.after(1600, function(){ 
			client.stop();
			take_pic(client, 0,2);
			});
		
		client.after(1600, function(){ client.stop(); });
		client.after(1600, function(){ client.front(0.2) });
		client.after(1600, function(){ 
			client.stop();

			take_pic(client, 0,3);
		});
		client.after(1600, function(){ client.stop(); });
		client.after(1600, function(){ client.counterClockwise(0.6); });
		client.after(1400, function(){ client.stop(); });
		client.after(1600, function(){ client.front(0.2); });
		client.after(1600, function(){ 
			client.stop();

			take_pic(client, 1,3);
		});
		
		client.after(1600, function(){ client.stop(); });
		client.after(1600, function(){ client.counterClockwise(0.6); });	
		client.after(1400, function(){ client.stop(); });
		client.after(1600, function(){ client.front(0.2); });
		client.after(1600, function(){ 
			client.stop();

			take_pic(client, 1,2);
		});
		
		client.after(1600, function(){ client.stop(); });
		client.after(1600, function(){ client.front(0.2); });
		client.after(1600, function(){ 
			client.stop();

			take_pic(client, 1,1);
		});
		
		client.after(1600, function(){ client.stop(); });
		client.after(50, function(){ client.land() });

	});
	
});

function take_pic(client, i, j) {
	var pngStream = client.getPngStream();
	pngStream.on('data', function(pngBuffer) {
		fs.writeFile('section' + i + '_' + j + '.png', pngBuffer, function(err) {
			if(err) console.log('error taking pic');
		});
		//fs.close();
	});

}