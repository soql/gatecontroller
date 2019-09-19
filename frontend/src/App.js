import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import './bootstrap.css';
import Collapsible from 'react-collapsible';
import PinchZoomPan from "react-responsive-pinch-zoom-pan";
import { Button } from 'react-bootstrap';
import {Carousel} from 'react-bootstrap';
import Loader from 'react-loader-spinner';

class App extends React.Component {
	 constructor(props) {
		    super(props);
		    this.state = {
		    		eventList: [],
		    		fotos: {},
		    		numberOfResults: 2
		    };
		  }
	getEventsFromServer(){
	    fetch('/rest/list?number='+this.state.numberOfResults)	      
	    .then(response => response.json())
	    .then(data => this.setState({ eventList: data }));	
	}
	componentDidMount() {
		this.getEventsFromServer();
	  }
	
	
	getMore(){
		this.setState({numberOfResults: this.state.numberOfResults+10},() => this.getEventsFromServer());
		
	}
	handleOpen(ide){	
		const copyFoto={...this.state.fotos}	
		if(this.state.fotos[ide])
			return;
		fetch('/rest/foto?id='+ide)
		.then(response => response.json())
	    .then(data => {
	    	copyFoto[ide]=data;
	    	this.setState({	    			    
	    	 	fotos: copyFoto
	    	})});
	}
	renderImage(event){
		this.state.fotos && this.state.fotos[event.id] && this.state.fotos[event.id].map((element, i) => {
			console.log("TUTAJ "+i);
		});
		return (this.state.fotos && this.state.fotos[event.id] && this.state.fotos[event.id][0])?
			<Carousel interval={null}>
				{this.state.fotos[event.id].map((element, idx) => {return (				
				<Carousel.Item key={event.id+"_"+idx}>
					<div>
					<PinchZoomPan maxScale={8} key={event.id+"_"+idx} initialScale={1}>			  		 		
						<img className={'resizedImage'} src={this.state.fotos[event.id][idx]} />
					</PinchZoomPan>
					</div>
		 		</Carousel.Item>)
		 		})}
	 		</Carousel>
	 		:	 		
	 		 <Loader
	         type="Bars"
	         color="#00BFFF"	       	         
	      />
	 		
	}
	render(){		
		const { events } = this.state;
		Object.keys(this.state.eventList).map((date_, i) => {
		console.log(date_+" "+i);
		});
		return (
		<div className="App">
			  {Object.keys(this.state.eventList).reverse().map((date_, i) => {return (
				  <Collapsible trigger={date_} classParentString={'Collapsible_date'}>		
				  	{this.state.eventList[date_].map((event, idx) =>{return (
				  		<Collapsible key={event.id} trigger={event.direction+" "+event.dateAsString} onOpen={this.handleOpen.bind(this, event.id)}>
				  		<div>
			  		 		{this.renderImage(event)}	
			  		 	</div>
			  		</Collapsible>)})}
				  </Collapsible>)
			  		
			  })}
			  <Button variant="outline-info" onClick={this.getMore.bind(this)}>Pobierz więcej</Button>
	    </div>)
	}
}

export default App;
