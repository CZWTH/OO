package ticTacToe.v490.controllers.local;

import ticTacToe.v490.controllers.ColocateController;
import ticTacToe.v490.controllers.CoordinateController;
import ticTacToe.v490.controllers.errors.ErrorGeneratorType;
import ticTacToe.v490.controllers.errors.ErrorReport;
import ticTacToe.v490.models.Coordinate;
import ticTacToe.v490.models.Game;

abstract class LocalColocateController extends LocalOperationController
		implements ColocateController {

	private LocalCoordinateController coordinateController;

	protected LocalColocateController(Game game,
			LocalCoordinateController coordinateController) {
		super(game);
		assert coordinateController != null;
		this.coordinateController = coordinateController;
	}

	public ErrorReport validateTarget(Coordinate target) {
		if (!this.empty(target)) {
			return ErrorGeneratorType.NOT_EMPTY.getErrorReport(this.getGame());
		}
		return null;
	}

	public CoordinateController getCoordinateController() {
		return coordinateController;
	}

}
