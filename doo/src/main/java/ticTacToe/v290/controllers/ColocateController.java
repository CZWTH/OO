package ticTacToe.v290.controllers;

import ticTacToe.v290.models.Game;
import ticTacToe.v290.models.State;
import ticTacToe.v290.utils.IO;

public abstract class ColocateController extends OperationController {

	private String actionTitle;
	
	private CoordinateController coordinateController;
	
	private TicTacToeCoordinate target;

	protected ColocateController(Game game, String actionTitle,
			CoordinateController coordinateController) {
		super(game);
		assert actionTitle != null;
		assert coordinateController != null;
		this.actionTitle = actionTitle;
		this.coordinateController = coordinateController;
	}

	public void control() {
		assert this.getState() == State.IN_GAME;
		IO io = new IO();
		io.writeln(actionTitle + " el jugador " + this.getTurn().take());
		this.colocate();
		this.getBoard().write();
		if (this.getBoard().existTicTacToe(this.getTurn().take())) {
			io.writeln("Victoria!!!! " + this.getTurn().take() + "! " + this.getTurn().take()
					+ "! " + this.getTurn().take() + "! Victoria!!!!");
			this.setState(State.FINAL);
		} else {
			this.getTurn().change();
		}
	}

	protected abstract void colocate();
	
	protected void put(String targetTitle) {
		Error error;
		do {
			target = coordinateController.getTarget(targetTitle);
			error = this.validateTarget();
			if (error != null){
				new IO().writeln(""+error);
			}
		} while (error != null);	
		this.getBoard().put(target, this.getTurn().take());
	}
	
	protected Error validateTarget(){
		if (!this.getBoard().empty(target)) {
			return Error.NOT_EMPTY;
		}
		return null;
	}
	
	protected CoordinateController getCoordinateController(){
		return coordinateController;
	}
	
	protected TicTacToeCoordinate getTarget(){
		return target;
	}

}