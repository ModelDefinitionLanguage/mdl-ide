package org.ddmore.mdl.validation;

import static org.eclipse.uomo.units.SI.GRAM;
import static org.eclipse.uomo.units.SI.METRE;
import static org.eclipse.uomo.units.SI.CUBIC_METRE;
import static org.eclipse.uomo.units.SI.MOLE;
import static org.eclipse.uomo.units.SI.SECOND;

import org.eclipse.uomo.units.AbstractSystemOfUnits;
import org.eclipse.uomo.units.AbstractUnit;
import org.unitsofmeasurement.quantity.*;
import org.unitsofmeasurement.unit.Unit;

public final class NonSI extends AbstractSystemOfUnits {

		private NonSI() {}

		static NonSI getInstance() {
			return INSTANCE;
		}

		private static final NonSI INSTANCE = new NonSI();

		// Dimensionless 
		static final Unit<Dimensionless> PERCENT = addUnit(AbstractUnit.ONE.divide(100));

		// Time 
		static final Unit<Time> MILLISECOND = addUnit(SECOND.divide(1000));
		static final Unit<Time> MICROSECOND = addUnit(MILLISECOND.divide(1000));
		static final Unit<Time> NANOSECOND = addUnit(MICROSECOND.divide(1000));
		static final Unit<Time> PICOSECOND = addUnit(NANOSECOND.divide(1000));
		static final Unit<Time> FEMTOSECOND = addUnit(PICOSECOND.divide(1000));

		static final Unit<Time> MINUTE = addUnit(SECOND.multiply(60));
		static final Unit<Time> HOUR = addUnit(MINUTE.multiply(60));
		static final Unit<Time> DAY = addUnit(HOUR.multiply(24));
		static final Unit<Time> WEEK = addUnit(DAY.multiply(7));
		static final Unit<Time> YEAR = addUnit(DAY.multiply(365));
		
		//Volume
		static final Unit<Volume> LITER = addUnit(CUBIC_METRE.divide(1000));
		static final Unit<Volume> DECILITER = addUnit(LITER.divide(10));
		static final Unit<Volume> MILLILITER = addUnit(LITER.divide(1000));
		static final Unit<Volume> MICROLITER = addUnit(MILLILITER.divide(1000));
		static final Unit<Volume> NANOLITER = addUnit(MICROLITER.divide(1000));
		static final Unit<Volume> PICOLITER = addUnit(NANOLITER.divide(1000));
		static final Unit<Volume> FEMTOLITER = addUnit(PICOLITER.divide(1000));
		
		//Mass
		static final Unit<Mass> MILLIGRAM = addUnit(GRAM.divide(1000));
		static final Unit<Mass> MICROGRAM = addUnit(MILLIGRAM.divide(1000));
		static final Unit<Mass> NANOGRAM = addUnit(MICROGRAM.divide(1000));
		static final Unit<Mass> PICOGRAM = addUnit(NANOGRAM.divide(1000));
		static final Unit<Mass> FEMTOGRAM = addUnit(PICOGRAM.divide(1000));
		
		//Amount of substance
		static final Unit<AmountOfSubstance> MILLIMOLE = addUnit(MOLE.divide(1000));
		static final Unit<AmountOfSubstance> MICROMOLE = addUnit(MILLIMOLE.divide(1000));
		static final Unit<AmountOfSubstance> NANOMOLE = addUnit(MICROMOLE.divide(1000));
		static final Unit<AmountOfSubstance> PICOMOLE = addUnit(NANOMOLE.divide(1000));
		static final Unit<AmountOfSubstance> FEMTOMOLE = addUnit(PICOMOLE.divide(1000));

		//Length
		static final Unit<Length> KILOMETRE = addUnit(METRE.multiply(1000));
		static final Unit<Length> SANTIMETRE = addUnit(METRE.divide(100));
		static final Unit<Length> MILLIMETRE = addUnit(METRE.divide(1000));
		static final Unit<Length> MICROMETRE = addUnit(MILLIMETRE.divide(1000));
		static final Unit<Length> NANOMETRE = addUnit(MICROMETRE.divide(1000));
		static final Unit<Length> PICOMETRE = addUnit(NANOMETRE.divide(1000));
		static final Unit<Length> FEMTOMETRE = addUnit(PICOMETRE.divide(1000));
		
		static final Unit<Length> FOOT = addUnit(METRE.multiply(3048).divide(10000));
		static final Unit<Length> INCH = addUnit(FOOT.divide(12));
		
		@Override
		public String getName() {
			return getClass().getSimpleName();
		}
}
